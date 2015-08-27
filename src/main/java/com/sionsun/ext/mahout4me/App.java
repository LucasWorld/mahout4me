package com.sionsun.ext.mahout4me;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
        System.out.println( "Hello World!" );
		try {
			DataModel model = new FileDataModel(new File("E:\\workspace_mahout\\mahout4me\\target\\classes\\data.txt"));
			
	        UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
	        
	        
	        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
	        
	        
	        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
	        
	        
	        
	        List<RecommendedItem> recommendations = recommender.recommend(3, 2);
	        for (RecommendedItem recommendation : recommendations) {
	        	 System.out.println(recommendation);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
