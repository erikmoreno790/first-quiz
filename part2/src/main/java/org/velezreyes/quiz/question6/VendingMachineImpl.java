package org.velezreyes.quiz.question6;

public class VendingMachineImpl implements VendingMachine {

  private boolean quarterInserted = false;
  private int moneyInserted = 0;
  private static VendingMachineImpl instance;

  private VendingMachineImpl(){
    //Constructor de la clase estatica 
  }
 
  public static VendingMachineImpl getInstance() {
    if (instance == null) {
      instance = new VendingMachineImpl();
    }
    return instance;
  }

  @Override
  public void insertQuarter() {
   
    quarterInserted = true;
    moneyInserted +=25;
  }

  @Override
  public Drink pressButton(String name) throws org.velezreyes.quiz.question6.NotEnoughMoneyException, org.velezreyes.quiz.question6.UnknownDrinkException {
    if (!quarterInserted){
      throw new NotEnoughMoneyException();
    }

    if("KarenTea".equals(name)){
      if(moneyInserted <= 75){
        throw new NotEnoughMoneyException();
      }
      quarterInserted = false;
      moneyInserted -= 75;
      return new Drink() {

        @Override
        public String getName() {
          return "KarenTea";
        }

        @Override
        public boolean isFizzy() {
          return false;
        } 
      };
    } else if("ScottCola".equals(name)){
      quarterInserted = false;
      return new Drink() {

        @Override
        public String getName() {
          return "ScottCola";
        }

        @Override
        public boolean isFizzy() {
          return true;
        }
      };
    } else {
      throw new UnknownDrinkException();
    }
  }
}
