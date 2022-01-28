package com.station.system.pay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.station.system.pay.Controller.PaymentController;
import com.station.system.pay.Entity.Payment;
import com.station.system.pay.Entity.Status;
import com.station.system.pay.Service.PaymentService;
import com.station.system.pay.Service.StatusService;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    final static Logger log = LoggerFactory.getLogger(ControllerTest.class);
    @Autowired
    ObjectMapper objectMapper;
    
     @Autowired
    private static MockMvc mockMvc;

    private static StatusService  mockStatusService;
    private static PaymentService mockPaymentService;
    private  List<Status>st=new ArrayList<>();
    private  List<Payment>pay=new ArrayList<>();
    @BeforeAll
    public static void init() {
    mockPaymentService=Mockito.mock(PaymentService.class);
      mockStatusService=Mockito.mock(StatusService.class);
    PaymentController paymentcontroller = new PaymentController(mockPaymentService,mockStatusService);
      mockMvc = MockMvcBuilders.standaloneSetup(paymentcontroller).build();
  }
  @BeforeEach
  public void fillList(){
    log.info("Before Each");
    Payment payment1=new Payment(1L,1L,1L,111,"1","1");
    Payment payment2=new Payment(2L,2L,2L,222,"2","2");
    Payment payment3=new Payment(3L,3L,3L,333,"3","3");
    Status status1=new Status(1L,1L,"new");
    Status status2=new Status(2L,2L,"fail");
    Status status3=new Status(3L,3L,"done");
        st.add(status1);
        st.add(status2);
        st.add(status3);
        pay.add(payment1);
        pay.add(payment2);
        pay.add(payment3);
       log.info("st:{}",st.size());

  }
  @AfterEach
  public void clearList(){
    st.clear();
    pay.clear();
  }

  @Test
  void testSaveStatus() throws Exception{
Status status=st.get(0);
mockStatusService.saveStatus(status);
assertEquals(status,st.get(0));
verify(mockStatusService,times(1)).saveStatus(status);
  }

  @Test
  void savePayment()throws Exception{
Payment p=pay.get(0);
mockPaymentService.savePay(p);
assertEquals(p,pay.get(0));
verify(mockPaymentService,times(1)).savePay(p);
  }

  
  @Test
  void pay()throws Exception{
    Payment py=pay.get(0);
    mockPaymentService.savePay(py);
    log.info("id:{}",py.getIdPayment());
    when(mockPaymentService.findPayById(py.getIdPayment())).thenReturn(py);
    when(mockPaymentService.findAll()).thenReturn(pay);
    Payment b =mockPaymentService.findPayById(py.getIdPayment());
    ArrayList<Payment> all = (ArrayList<Payment>)mockPaymentService.findAll();
    assertEquals(py,b);
    assertEquals(all.size(),pay.size()); 
    verify(mockPaymentService,times(2)).savePay(py);
  }

  @Test
  void pay1() throws Exception{
    Payment p=pay.get(0);

       mockMvc.perform(MockMvcRequestBuilders
        .post("http://localhost:8080/payment")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(p))
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
