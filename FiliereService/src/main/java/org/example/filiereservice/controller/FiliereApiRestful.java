package org.example.filiereservice.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.example.filiereservice.dto.RequestFiliereDto;
import org.example.filiereservice.dto.ResponseFiliereDto;
import org.example.filiereservice.services.FiliereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des filières",
                description = "Cette API offre toutes les méthodes pour gérer les filières",
                version = "1.0"
        ),
        servers = @Server(
                url = "http://localhost:8082"
        )
)

@RestController
@RequestMapping("/v1/filieres")
public class FiliereApiRestful {

    private FiliereService filiereService;

    public FiliereApiRestful(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @Operation(
            summary = "Ajouter une filière",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestFiliereDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Filière bien enregistrée",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "erreur server")
            }
    )

    @PostMapping
    public ResponseEntity<ResponseFiliereDto> add(@RequestBody RequestFiliereDto requestFiliereDto) {
        ResponseFiliereDto responseFiliereDto = filiereService.Add_Filiere(requestFiliereDto);
        return ResponseEntity.ok(responseFiliereDto);
    }

    @Operation(
            summary = "Récupérer toutes les filières",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste de toutes les filières",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @GetMapping
    public ResponseEntity<List<ResponseFiliereDto>> getAll() {
        List<ResponseFiliereDto> filiereDtos = filiereService.GetAllFilieres();
        return ResponseEntity.ok(filiereDtos);
    }

    @Operation(
            summary = "Récupérer une filière par son identifiant",
           /* parameters = {
                    @io.swagger.v3.oas.annotations.parameters.Parameter(
                            name = "id",
                            description = "Identifiant de la filière à récupérer",
                            required = true,
                            schema = @Schema(type = "integer")
                    )
            },*/
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Détails de la filière",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Filière non trouvée"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFiliereDto> get(@PathVariable Integer id) {
        ResponseFiliereDto responseFiliereDto = filiereService.GetFiliereById(id);
        return ResponseEntity.ok(responseFiliereDto);
    }

    @Operation(
            summary = "Mettre à jour une filière",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestFiliereDto.class)
                    )
            ),
            /*parameters = {
                    @io.swagger.v3.oas.annotations.parameters.Parameter(
                            name = "id",
                            description = "Identifiant de la filière à mettre à jour",
                            required = true,
                            schema = @Schema(type = "integer")
                    )
            },*/
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Filière mise à jour",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Filière non trouvée"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFiliereDto> update(@PathVariable Integer id, @RequestBody RequestFiliereDto requestFiliereDto) {
        ResponseFiliereDto responseFiliereDto = filiereService.Update_Filiere(id, requestFiliereDto);
        return ResponseEntity.ok(responseFiliereDto);
    }

    @Operation(
            summary = "Supprimer une filière",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Filière non trouvée"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        filiereService.DeleteFiliereById(id);
        return ResponseEntity.ok().build();
    }
}