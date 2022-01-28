package com.tickets.pay.station;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tickets.pay.station.Controller.InfoController;
import com.tickets.pay.station.Entity.Info;
import com.tickets.pay.station.Service.InfoService;
import com.mysql.cj.protocol.x.Ok;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class InfoControllerTest {
    @InjectMocks
   InfoController mocksInfoController = new InfoController(mockInfoService);

   @Autowired
   ObjectMapper objectMapper;
   
   
    @Autowired
   private static MockMvc mockMvc;

   final static Logger log = LoggerFactory.getLogger(InfoControllerTest.class);
   private static  InfoService mockInfoService;
   private  List<Info>info=new ArrayList<>();

   @BeforeAll
   public static void init() {
    mockInfoService=Mockito.mock(InfoService.class);
     
    InfoController infocontroller = new InfoController(mockInfoService);
     mockMvc = MockMvcBuilders.standaloneSetup(infocontroller).build();
 }
 @BeforeEach
 public void fillList(){
   log.info("Before Each");
   
   Info info1=new Info(1L,1L,1L,"new");
   Info info2=new Info(2L,2L,2L,"fail");
   Info info3=new Info(3L,3L,3L,"done");
       info.add(info1);
       info.add(info2);
       info.add(info3);
 }
 @AfterEach
 public void clearList(){
   info.clear();
 }

 @Test
 void getInfoTicket(){
     Info in=info.get(0);
     when(mockInfoService.findById(in.getIdTicket())).thenReturn(in);
     Info a=mockInfoService.findById(in.getIdTicket());
     assertEquals(in,a);
     verify(mockInfoService,times(1)).findById(in.getIdInfo());
 }

 @Test
 void saveInfo(){
     Info in=info.get(0);
    mockInfoService.save(in);
     verify(mockInfoService,times(1)).save(in);
 }

}
