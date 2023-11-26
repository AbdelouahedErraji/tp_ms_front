package com.example.msachat.services;

import com.example.msachat.dto.AchatReq;
import com.example.msachat.dto.AchatResponse;

import java.util.List;

public interface AchatService {
    public AchatResponse add(AchatReq achatReq);
    public AchatResponse getAchat(Long id) throws Exception;
    public List<AchatResponse> allAchats();
}
