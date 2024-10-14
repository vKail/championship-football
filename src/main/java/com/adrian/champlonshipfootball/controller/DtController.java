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
    public List<Dt> getDts() {
        return dtService.findAllDts();
    }

    @GetMapping("/dts/{id}")
    public Dt getDtById(@PathVariable Long id) {
        return dtService.findDtById(id);
    }

    @PostMapping("/dts")
    public Dt saveDt(@RequestBody Dt dt) {
        return dtService.saveDt(dt);
    }

    @PutMapping("/dts/{id}")
    public Dt updateDt(@PathVariable Long id, @RequestBody Dt dt) {
        return dtService.updateDt(id, dt);
    }

    @DeleteMapping("/dts/{id}")
    public void deleteDt(@PathVariable Long id) {
        dtService.deleteDt(id);
    }
}
