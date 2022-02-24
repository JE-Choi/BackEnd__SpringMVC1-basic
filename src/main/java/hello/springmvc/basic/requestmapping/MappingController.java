package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping(value = {"hello-basic", "hello-go"})
    public String helloBasic() {
        log.info("hello-basic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략가능
     * @PathVariable("userId") String userId → @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * 다중 매핑
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터 조건 매핑
     */
    @GetMapping(value = "mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     */
    @GetMapping(value = "mapping-param", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes = "!application/json"
     * consumes= = "application/*"
     * consumes = "*\/*"
     * ** 컨트롤러 입장에서 소비하는 입장 : 요청의 content-type 이라서 consumes
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsume(){
        log.info("mappingConsume");
        return "ok";
    }

    /**
     * Accept 헤더기반 Media Type
     * Accept: 클라이언트 입장에서 받아드릴 수 있는 Media Type을 지정한 것
     * ** 컨트롤러 입장에서 생산하는 입장
     */
    @PostMapping(value = "mapping-produces", produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
