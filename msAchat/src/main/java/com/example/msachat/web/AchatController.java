package com.example.msachat.web;

import com.example.msachat.dto.AchatReq;
import com.example.msachat.dto.AchatResponse;
import com.example.msachat.services.AchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/achat")
public class AchatController {
    @Autowired
    AchatService achatService;
    @PostMapping("/add")
    public AchatResponse add(@RequestBody AchatReq achatReq){
        return achatService.add(achatReq);
    }
    @GetMapping("{id}")
    public AchatResponse getAchat(@PathVariable("id") Long id) throws Exception {
        return achatService.getAchat(id);
    }
    @GetMapping("achats")
    public List<AchatResponse> allAchats() {
        return achatService.allAchats();
    }
}
