package org.example.banqueservice.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.example.banqueservice.dto.RequestCompteDto;
import org.example.banqueservice.dto.ResponseCompteDto;
import org.example.banqueservice.service.CompteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des comptes bancaires",
                description = "Cette API offre toutes les méthodes pour gérer les comptes",
                version = "1.0"
        ),
        servers = @Server(
                url = "http://localhost:8081"
        )
)

@RestController
@RequestMapping("/v1/comptes")
public class ApiRestful {

    private CompteServiceImpl compteService;

    public ApiRestful(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }

    @Operation(
            summary = "Ajouter un compte",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "bien enregistré",
                            content = @Content(
                                    mediaType = "appilication/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "erreur server")
            }
    )

    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.Add_Compte(requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getAll() {
        List<ResponseCompteDto> CompteDtos = compteService.GetAllCompte();
        return ResponseEntity.ok(CompteDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> get(@PathVariable Integer id) {
        ResponseCompteDto responseCompteDto = compteService.GetCompteById(id);
        return ResponseEntity.ok(responseCompteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> update(@PathVariable Integer id, @RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.Update_Compte(id, requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        compteService.DeleteCompteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/crediter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable Integer id, @PathVariable Double m) {
        ResponseCompteDto responseCompteDto = compteService.Crediter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }

    @PatchMapping("/debiter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable Integer id, @PathVariable Double m) {
        ResponseCompteDto responseCompteDto = compteService.Debiter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }

    //
}