package com.station.system.pay.ServiceImpl;

import java.util.Random;

import com.station.system.pay.Dao.StatusDao;
import com.station.system.pay.Entity.Status;
import com.station.system.pay.Service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StatusServiceImpl implements StatusService {

private static final Logger log = LoggerFactory.getLogger(StatusServiceImpl.class);

Random random = new Random();

String [] statusList={"Fail","New","Done"};//

@Autowired
StatusDao statusdao;
    @Override
    public void saveStatus(Status status) {
     String randomStatus = statusList[random.nextInt(statusList.length)];
        log.info("Random status:{}",randomStatus);
        status.setStatus(randomStatus);
       this.statusdao.save(status);
        
    }

    @Override
    public Status findStatusById(Long id) {
        return this.statusdao.getById(id);
    }

    
   
}
