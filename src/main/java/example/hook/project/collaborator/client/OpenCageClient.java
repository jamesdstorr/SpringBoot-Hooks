package example.hook.project.collaborator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openCageClient", url = "${opencage.api.url}")
public interface OpenCageClient {
    @GetMapping("/json")
    String getCoordinates(
            @RequestParam("q") String location,
            @RequestParam("key") String apiKey);
}
