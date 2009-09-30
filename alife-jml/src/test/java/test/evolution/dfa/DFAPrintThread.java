package test.evolution.dfa;

class DFAPrintThread extends Thread {
  protected boolean go = true;
  protected long running_time;
  protected int number_of_intervals;
  protected int count = 0;
  protected boolean print = false;
  protected boolean wakeUp = false;

  protected DFAEvolver evolver;
  public DFAPrintThread( DFAEvolver _evolver, long _running_time,
                         int _number_of_intervals ){
    number_of_intervals = _number_of_intervals;
    evolver = _evolver;
    running_time = _running_time;
  }

  public void asleep( long sleep_time ) throws Exception{
    wakeUp = false;
    int i=0;
    while( i<sleep_time && !wakeUp ){
      sleep( 1 );
      i++;
    }
    wakeUp = true;
  }

  public void run(){
    try{
      long sleep_time = running_time / number_of_intervals;
      count = 0;
      while( count < number_of_intervals && go ){
        sleep(sleep_time);
        print = true;
        count++;
      }
      go = false;
    }catch( Exception e ){ e.printStackTrace(); }
  }

  public static void main( String[] argv ){
    DFAPrintThread th1 = new DFAPrintThread( null, 20000, 1 );
    th1.start();
    try{
      System.out.println("hello..");
      sleep(10000);
      System.out.println("bye main..");
      th1.wakeUp = true;
    }catch( Exception e ){}
  }

}

