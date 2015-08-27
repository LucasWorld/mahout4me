package com.sionsun.ext.mahout4me;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
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
public class App {
	static final String inputFile = "/home/hadoop-mahout/Downloads/ml-1m/ratings.dat";
	static final String outputFile = "/home/hadoop-mahout/Downloads/ml-1m/ratings.csv";

	public static void main(String[] args) {
		System.out.println("Hello World!");

		try {
			createCsvRatingsFile();
			DataModel model = new FileDataModel(new File(outputFile));
			SVDRecommender slopeOneRecommender = new SVDRecommender(model, null);
			CachingRecommender cachingRecommender = new CachingRecommender(slopeOneRecommender);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 
	 * */
	private static void createCsvRatingsFile() throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		String line = null;
		String line2write = null;

		String[] temp;
		int i = 0;
		while ((line = br.readLine()) != null && i < 10000) {
			i++;
			temp = line.split("::");
			line2write = temp[0] + "," + temp[1];
			bw.write(line2write);
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
