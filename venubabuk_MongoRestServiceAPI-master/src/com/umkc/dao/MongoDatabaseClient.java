package com.umkc.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDatabaseClient {
	
	//Creating DBCollection for ASE Collection in database group13
	private static DBCollection createASEDBCollection(){
		
		MongoClientURI mongoClientUri = new MongoClientURI("mongodb://root:admin@ds045714.mongolab.com:45714/group13");
		
		MongoClient mongoclient = new MongoClient(mongoClientUri);
		
		DB db =mongoclient.getDB(mongoClientUri.getDatabase());
		
		DBCollection dbcollection = db.getCollection("ase");
		
		return dbcollection;
	}
	
	public boolean performLoginValidation(BasicDBObject basicdbobject){
		
		DBCollection dbcollection = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(basicdbobject);
		
		System.out.println("Hiii");
		
		BasicDBObject basicsDBObject;
		
		while(dbcursor.hasNext()){
			
			BasicDBObject basiDBObject = (BasicDBObject) dbcursor.next();
			
			System.out.println("Inside hello how are you");
			
			//return basiDBObject.toString();
			
			String pwd = basiDBObject.getString("Password");
			String userType = basiDBObject.getString("usertype");
			
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(DBObject basicdbobject){
		
		System.out.println(basicdbobject.toString());
		
		DBCollection dbcollection = createASEDBCollection();
		
		if(doesASERecordExits((BasicDBObject) basicdbobject)){
			
			System.out.println("record exists");
			return false;
			
		}else{
			
			System.out.println("No record exists so trying to insert");
		
		dbcollection.insert(basicdbobject);
		
		return true;
		
		}
		
		
	}
	//Checking weather ase record exists or not
	public boolean doesASERecordExits(BasicDBObject dbobject) {
		
		DBCollection dbcollection = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(dbobject);
		
		if(dbcursor.hasNext()){
			return true;
		}
		
		return false;
	}
	
	public String retrieveMatchingRecord(BasicDBObject basicDBObject){
		
		DBCollection dbcollection = createASEDBCollection();
		
		DBCursor dbCursor = dbcollection.find(basicDBObject);
		return dbCursor.toArray().toString();
	}
}
