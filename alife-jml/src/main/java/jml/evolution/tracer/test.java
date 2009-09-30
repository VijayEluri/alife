package jml.evolution.tracer;


public class test {

	 /**
    * Singleton dessign pattern.
    */
   private static test instance;
	
	
	/**
    * Returns the only one instance allwoed for the class.
    * @return The class instance.
    */
   public static synchronized test getInstance() {
       if(instance == null){
           instance = new test();
       }
       return instance;           
   }

}
