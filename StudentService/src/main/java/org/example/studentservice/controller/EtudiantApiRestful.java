package org.example.studentservice.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.example.studentservice.dto.RequestEtudiantDto;
import org.example.studentservice.dto.ResponseEtudiantDto;
import org.example.studentservice.services.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des étudiants",
                description = "Cette API offre toutes les méthodes pour gérer les étudiants",
                version = "1.0"
        ),
        servers = @Server(
                url = "http://localhost:8081"
        )
)

@RestController
@RequestMapping("/v1/etudiants")
public class EtudiantApiRestful {

    private EtudiantService etudiantService;

    public EtudiantApiRestful(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @Operation(
            summary = "Ajouter un étudiant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Étudiant bien enregistré",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "erreur server")
            }
    )

    @PostMapping
    public ResponseEntity<ResponseEtudiantDto> add(@RequestBody RequestEtudiantDto requestEtudiantDto) {
        ResponseEtudiantDto responseEtudiantDto = etudiantService.Add_Etudiant(requestEtudiantDto);
        return ResponseEntity.ok(responseEtudiantDto);
    }

    @Operation(
            summary = "Récupérer tous les étudiants",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste de tous les étudiants",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @GetMapping
    public ResponseEntity<List<ResponseEtudiantDto>> getAll() {
        List<ResponseEtudiantDto> etudiantDtos = etudiantService.GetAllEtudiants();
        return ResponseEntity.ok(etudiantDtos);
    }

    @Operation(
            summary = "Récupérer un étudiant par son identifiant",
           /* parameters = {
                    @io.swagger.v3.oas.annotations.parameters.Parameter(
                            name = "id",
                            description = "Identifiant de l'étudiant à récupérer",
                            required = true,
                            schema = @Schema(type = "integer")
                    )
            }, */
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Détails de l'étudiant",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> get(@PathVariable Integer id) {
        ResponseEtudiantDto responseEtudiantDto = etudiantService.GetEtudiantById(id);
        return ResponseEntity.ok(responseEtudiantDto);
    }

    @Operation(
            summary = "Mettre à jour un étudiant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDto.class)
                    )
            ),
           /* parameters = {
                    @io.swagger.v3.oas.annotations.parameters.Parameter(
                            name = "id",
                            description = "Identifiant de l'étudiant à mettre à jour",
                            required = true,
                            schema = @Schema(type = "integer")
                    )
            }, */
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Étudiant mis à jour",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> update(@PathVariable Integer id, @RequestBody RequestEtudiantDto requestEtudiantDto) {
        ResponseEtudiantDto responseEtudiantDto = etudiantService.Update_Etudiant(id, requestEtudiantDto);
        return ResponseEntity.ok(responseEtudiantDto);
    }

    @Operation(
            summary = "Supprimer un étudiant",
           /* parameters = {
                    @io.swagger.v3.oas.annotations.parameters.Parameter(
                            name = "id",
                            description = "Identifiant de l'étudiant à supprimer",
                            required = true,
                            schema = @Schema(type = "integer")
                    )
            }, */
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        etudiantService.DeleteEtudiantById(id);
        return ResponseEntity.ok().build();
    }
}