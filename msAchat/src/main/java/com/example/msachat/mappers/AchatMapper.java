package com.example.msachat.mappers;

import com.example.msachat.dto.AchatReq;
import com.example.msachat.dto.AchatResponse;
import com.example.msachat.entities.Achat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AchatMapper {
    public AchatResponse fromAchat(Achat achat){
        AchatResponse achatResponse = new AchatResponse();
        BeanUtils.copyProperties(achat,achatResponse);
        return achatResponse;
    }
    ///-------------------------------
    public Achat fromAchatReq(AchatReq achatReq){
        Achat achat = new Achat();
        BeanUtils.copyProperties(achatReq,achat);
        return achat;
    }
}
