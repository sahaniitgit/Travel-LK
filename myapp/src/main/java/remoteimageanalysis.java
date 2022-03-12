import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.*;

import java.util.ArrayList;
import java.util.List;

public class remoteimageanalysis {
    static String subscriptionKey = "b3269c4593e64cd3aad19ff12eb36a12";
    static String endpoint = "https://sdgp.cognitiveservices.azure.com/";
    public static void main(String[] args) {

        System.out.println("\nAzure Cognitive Services Computer Vision - Java Quickstart Sample");

        // Create an authenticated Computer Vision client.
        ComputerVisionClient compVisClient = Authenticate(subscriptionKey, endpoint);

        // Analyze local and remote images
        AnalyzeRemoteImage(compVisClient);

    }

    public static ComputerVisionClient Authenticate(String subscriptionKey, String endpoint) {
        return ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint);
    }

    public static void AnalyzeRemoteImage(ComputerVisionClient compVisClient) {
        /*
         * Analyze an image from a URL:

         */
        String pathToRemoteImage = "https://www.colombocitycentre.lk/wp-content/uploads/2018/09/Arium-1170x658.jpg";
        // This list defines the features to be extracted from the image.
        List<VisualFeatureTypes> featuresToExtractFromRemoteImage = new ArrayList<>();
        featuresToExtractFromRemoteImage.add(VisualFeatureTypes.DESCRIPTION);
        featuresToExtractFromRemoteImage.add(VisualFeatureTypes.CATEGORIES);
        featuresToExtractFromRemoteImage.add(VisualFeatureTypes.TAGS);

        System.out.println("\n\nAnalyzing an image from a URL ...");

        try {
            // Call the Computer Vision service and tell it to analyze the loaded image.
            ImageAnalysis analysis = compVisClient.computerVision().analyzeImage().withUrl(pathToRemoteImage)
                    .withVisualFeatures(featuresToExtractFromRemoteImage).execute();

            // Display image captions and confidence values.
            System.out.println("\nCaptions: ");
            for (ImageCaption caption : analysis.description().captions()) {
                System.out.printf("\'%s\' with confidence %f\n", caption.text(), caption.confidence());
            }

            // Display image category names and confidence values.
            System.out.println("\nCategories: ");
            for (Category category : analysis.categories()) {
                System.out.printf("\'%s\' with confidence %f\n", category.name(), category.score());
            }

            // Display image tags and confidence values.
            System.out.println("\nTags: ");
            ArrayList<String> tags = new ArrayList<String>();
            for (ImageTag tag : analysis.tags()) {
                System.out.printf("\'%s\' with confidence %f\n", tag.name(), tag.confidence());
                tags.add(tag.name());

            }
            System.out.println("tags are :" + tags);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
