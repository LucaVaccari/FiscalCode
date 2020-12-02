package it.castelli;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CSVParser
{
	public static ArrayList<String[]> getColumns(String filePath, int... indexes)
	{
		try
		{
			Scanner sc = new Scanner(new FileInputStream(filePath));
			ArrayList<String[]> returnMatrix = new ArrayList<String[]>();
			String line;

			// skip the first line
			sc.nextLine();

			while (sc.hasNext())
			{
				String[] selectedTokens = new String[indexes.length];
				line = sc.nextLine();
				String[] tokens = line.split(";");
				for (int i = 0; i < indexes.length; i++)
				{
					selectedTokens[i] = tokens[indexes[i]];
				}

				returnMatrix.add(selectedTokens);
			}

			return returnMatrix;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	public static HashMap<String, String> arrayListToHashMap(ArrayList<String[]> inputMatrix) throws IllegalArgumentException
	{
		HashMap<String, String> returnMap = new HashMap<String, String>(inputMatrix.size());

		for (int i = 0; i < inputMatrix.size(); i++)
		{
			if (inputMatrix.get(i).length != 2)
				throw new IllegalArgumentException();

			returnMap.put(inputMatrix.get(i)[0], inputMatrix.get(i)[1]);
		}

		return returnMap;
	}
}
