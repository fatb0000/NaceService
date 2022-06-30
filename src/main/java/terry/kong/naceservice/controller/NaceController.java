package terry.kong.naceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import terry.kong.naceservice.controller.dto.ErrorResponse;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.entity.Nace;
import terry.kong.naceservice.exception.NaceException;
import terry.kong.naceservice.service.NaceService;

import javax.xml.ws.Response;

@RestController
@RequestMapping(path = "/api")
public class NaceController {
    private NaceService naceService;
    @Autowired
    public NaceController(NaceService naceService){
        this.naceService = naceService;
    }
    @RequestMapping(value="/nace/{order}",method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable Long order){
        return ResponseEntity.ok(naceService.getNaceByOrder(order));
    }

    @RequestMapping(path="/nace",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@RequestBody NaceDto naceDto)  {
        try {
            return ResponseEntity.ok(naceService.update(naceDto));
        } catch (NaceException e) {
            throw new ResponseStatusException(
                    e.getStatus(), e.getMessage(), e);
        }
    }
    @RequestMapping(path="/nace",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> add(@RequestBody NaceDto naceDto) {
        try {
            return ResponseEntity.ok(naceService.save(naceDto));
        } catch (NaceException e) {
            throw new ResponseStatusException(
                    e.getStatus(), e.getMessage(), e);
        }
    }

}
