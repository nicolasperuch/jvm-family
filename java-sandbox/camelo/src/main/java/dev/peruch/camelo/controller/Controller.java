package dev.peruch.camelo.controller;

import dev.peruch.camelo.controller.dto.ClientDto;
import dev.peruch.camelo.controller.dto.ClientDtoResponse;
import dev.peruch.camelo.model.ClientModel;
import dev.peruch.camelo.service.RequestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.camel.ProducerTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class Controller {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Home page",
                  response = String.class,
                  notes = "Home page from Api")
    @ApiResponses(value = {
            @ApiResponse(
                    code=200,
                    message="Return home page message",
                    response=String.class)
    })
    @GetMapping("/")
    public ResponseEntity home(){
        return ok("Home");
    }


    @ApiOperation(value = "Business endpoint",
            response = String.class,
            notes = "Business page from Api")
    @ApiResponses(value = {
            @ApiResponse(
                    code=200,
                    message="Return a value",
                    response=String.class)
    })
    @PostMapping("/business")
    public ResponseEntity business(@RequestBody ClientDto clientDto){
        Object response = Stream.of(clientDto)
                                    .map(this::convertToModel)
                                    .map(c -> producerTemplate.requestBody("direct:smallLogic", c))
                                    .map(c -> convertToDto((ClientModel) c))
                                    .findFirst()
                                    .get();
        return ok(response);
    }

    @GetMapping("/heroes")
    public ResponseEntity heroes(){
        return ok(requestService.getHeroes());
    }

    @GetMapping("/hero/{id}/matches")
    public ResponseEntity heroMatchesById(@PathVariable String id){
        return ok(requestService.getHeroMatchesById(id));
    }

    private ClientDtoResponse convertToDto(ClientModel clientModel) {
        return modelMapper.map(clientModel, ClientDtoResponse.class);
    }

    private ClientModel convertToModel (ClientDto clientDto) {
        return modelMapper.map(clientDto, ClientModel.class);
    }
}