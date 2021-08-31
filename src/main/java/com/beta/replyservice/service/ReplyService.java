package com.beta.replyservice.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.digester.Rules;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

	  private enum RulesModel {
		    REVERSE,
		    MD5Converstion
		  }
	
	public String adoptToRule(String inputString) {
		
		String rules = inputString.split("-")[0];
		String stringValue = inputString.split("-")[1];
				
		char[] rulesDigits = rules.toCharArray();
		String resultString = stringValue;
		for(int i=0; i<rulesDigits.length; i++) {
			int digit=Character.getNumericValue(rulesDigits[i]);  
			RulesModel rulesEnum = RulesModel.values()[digit - 1];			
			switch (rulesEnum) {
			case MD5Converstion:
				resultString = this.convertStringTomd5(resultString);
				break;
			case REVERSE:
				resultString = this.performStringReverse(resultString);
				break;

			default:
				System.out.println("No Matches Found!!");
				break;
			}

		}
		
		return resultString;

	}
	
	private String performStringReverse(String performStringReverse) {
	    StringBuilder sb=new StringBuilder(performStringReverse);  
	    sb.reverse();  
	    return sb.toString();  
	}
	
    private String convertStringTomd5(String inputString) {
        try {
              // Create MD5 Hash
              MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
              digest.update(inputString.getBytes());
              byte messageDigest[] = digest.digest();

              // Create Hex String
              StringBuffer hexString = new StringBuffer();
              for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

              return hexString.toString();
            }catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
            }
            return "";
       }
}
