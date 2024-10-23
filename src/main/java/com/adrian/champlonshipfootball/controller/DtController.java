package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Dt;
import com.adrian.champlonshipfootball.service.DtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DtController {
    private final DtService dtService;

    public DtController(DtService dtService) {
        this.dtService = dtService;
    }

    @GetMapping("/dts")
    public List<Dt> getDts() throws Exception {
        try {
            return dtService.findAllDts();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/dts/{id}")
    public Dt getDtById(@PathVariable Long id) throws Exception {
        try {
            return dtService.findDtById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/dts")
    public Dt saveDt(@RequestBody Dt dt) throws Exception {
        try {
            return dtService.saveDt(dt);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/dts/{id}")
    public Dt updateDt(@PathVariable Long id, @RequestBody Dt dt) throws Exception {
        try {
            return dtService.updateDt(id, dt);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/dts/{id}")
    public void deleteDt(@PathVariable Long id) throws Exception {
        try {
            dtService.deleteDt(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
