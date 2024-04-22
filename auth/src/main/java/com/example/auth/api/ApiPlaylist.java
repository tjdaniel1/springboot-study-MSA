package com.example.auth.api;

import com.example.auth.dto.request.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor

public class ApiPlaylist {
    private final FeignPlaylist feignPlaylist;
    public static List<Map<String, Object>> failList = new ArrayList<>();
    @Async
    public void updatePlaylist(UpdateRequest updateRequest, Long id) {
        try {
            feignPlaylist.updatePlaylistUser(updateRequest, id);

        }catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("request", updateRequest);
            failList.add(map);
        }
    }
    @Scheduled(cron = "*/3 * * * * *")
    public void send(){
        failList.forEach(map -> {
            updatePlaylist((UpdateRequest)map.get("request"), (Long)map.get("id"));
        });
    }
}
