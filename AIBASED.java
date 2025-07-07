import java.io.File;
import java.util.List;
public class RecommendationEngine {
    public static void main(String[] args) {
        try {
            // Load sample data
            DataModel model = new FileDataModel(new File("user_preferences.csv"));
            
            // Create user similarity metric
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            
            // Define neighborhood (similar users)
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
            
            // Create recommender
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            
            // Generate recommendations for user 1 (you can change this ID)
            List<RecommendedItem> recommendations = recommender.recommend(1, 5);
            
            // Print recommendations
            System.out.println("Recommended products:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Product ID: " + recommendation.getItemID() + 
                                  " | Estimated Preference: " + recommendation.getValue());
            }
        } catch (Exception e) {
            System.err.println("Error in recommendation engine: " + e.getMessage());
        }
    }
}
