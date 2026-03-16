/**
 * Configuration class for Olimpia request parameters.
 * <p>
 * This class holds the configuration properties for the Olimpia API, including request parameters,
 * document types, and document requests.
 * </p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mb-api-olimpia")
public class OlimpiaRequestParam {

  private Map<String, OkMbInitiateConfig> request;

  private Map<String, IdDocTypes> documents;

  private Map<String, DocumentRequest> parameters;

  /**
   * Retrieves the ID document types configuration.
   *
   * @param olimpiaRequestParam the Olimpia request parameters
   * @return the ID document types configuration
   */
  public OkMbInitiateConfig getInitiateConfig(OlimpiaRequestParam olimpiaRequestParam) {
    var parameters = olimpiaRequestParam.request;

    if (ObjectUtils.isEmpty(parameters)) {
      throw new BusinessValidationException("Parameters config not found");
    }

    var config = parameters.get("validation");
    if (Objects.isNull(config)) {
      throw new BusinessValidationException(
          "Parameters type request doesn't exist");
    }

    return config;
  }

  /**
   * Retrieves the document types configuration.
   *
   * @param olimpiaRequestParam the Olimpia request parameters
   * @return the ID document types configuration
   */
  public DocumentRequest getDocumentConfig(OlimpiaRequestParam olimpiaRequestParam) {
    var documentsRequest = olimpiaRequestParam.parameters.get("document");

    if (Objects.isNull(documentsRequest)) {
      throw new BusinessValidationException(
          "Parameters type request document doesn't exist");
    }
    return documentsRequest;
  }

  /**
   * Configuration for ID document types.
   * <p>
   * This class holds the configuration properties for ID document types, including main legal
   * document type, front and back ID document types, and whether to hash the main legal document
   * type.
   * </p>
   */
  @Data
  @Builder
  public static class IdDocTypes {

    private String mainLegalDocType;
    private String frontIdDocumentType;
    private String backIdDocumentType;
    private boolean hashMainLegalDocType;
  }

  /**
   * Configuration for document requests.
   * <p>
   * This class holds the configuration properties for document requests, including white list
   * extensions, source system, and origin system.
   * </p>
   */
  @Data
  @Builder
  public static class DocumentRequest {

    private List<String> whiteListExtension;
    private String sourceSystem;
    private String originSystem;
  }
