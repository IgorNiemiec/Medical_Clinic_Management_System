package org.example.medical_clinic_management_system.parse;

import org.example.medical_clinic_management_system.dto.pharmacy.DrugRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class RplApiImporterService
{

    private static final Logger log = LoggerFactory.getLogger(RplApiImporterService.class);

    private final WebClient webClient;


    private static final String API_BASE_URL = "https://rejestry.ezdrowie.gov.pl";
    private static final String API_PATH = "/api/rpl/medicinal-products/search/public";
    private static final int PAGE_SIZE = 100;


    public RplApiImporterService() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader("User-Agent", "MedicalClinic-Importer-1.0 (Integration with eZdrowie RPL API)")
                .build();
    }

    public List<DrugRequestDto> fetchAndParseDrugs(String queryText) {

        String fullUri = UriComponentsBuilder.fromPath(API_PATH)
                .queryParam("name", queryText)
                .queryParam("subjectRolesIds", 1) // 1 = Ludzki
                .queryParam("isAdvancedSearch", false)
                .queryParam("size", PAGE_SIZE)
                .queryParam("page", 0)
                .queryParam("sort", "name,ASC")
                .toUriString();


        try {

            RplApiResponse response = webClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("API error: " + clientResponse.statusCode() + ", Body: " + body)))
                    .bodyToMono(RplApiResponse.class)
                    .block(); // Synchroniczne pobieranie

            if (response == null || response.getContent() == null || response.getContent().isEmpty()) {
                return List.of();
            }



            return response.getContent().stream()
                    .filter(this::isHumanDrug)
                    .map(this::mapRplDtoToDrugRequestDto)
                    .toList();

        } catch (Exception e) {

            return List.of();
        }
    }

    private DrugRequestDto mapRplDtoToDrugRequestDto(RplDrugProductDto rplDto) {
        DrugRequestDto dto = new DrugRequestDto();
        String fullProductName = rplDto.getMedicinalProductName() + " " + rplDto.getMedicinalProductPower();

        dto.setProductName(fullProductName);
        dto.setCommonName(rplDto.getCommonName());
        dto.setForm(rplDto.getFormName());
        dto.setAtcCode(rplDto.getAtcCode());

        String mockGtin = String.format("%014d", rplDto.getId() % 9999999999999L + 59000000000000L);
        dto.setGtinNumber(mockGtin);

        return dto;
    }


    private boolean isHumanDrug(RplDrugProductDto rplDto) {
        return rplDto.getAtcCode() != null && !rplDto.getAtcCode().toUpperCase().startsWith("Q");
    }



}
