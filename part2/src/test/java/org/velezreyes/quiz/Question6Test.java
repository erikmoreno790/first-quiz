package org.velezreyes.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.velezreyes.quiz.question6.Drink;
import org.velezreyes.quiz.question6.NotEnoughMoneyException;
import org.velezreyes.quiz.question6.UnknownDrinkException;
import org.velezreyes.quiz.question6.VendingMachine;
import org.velezreyes.quiz.question6.VendingMachineImpl;

public class Question6Test {

  private VendingMachine vm;

  @BeforeEach
  public void setUp(){
    //Creamos una nueva instancia de la maquina expendedora antes de cada prueba
    vm = VendingMachineImpl.getInstance();


     // Reinicia la variable moneyInserted a cero
    /*  try {
      vm.pressButton("ScottCola");
  } catch (Exception e) {

  }*/
  }

  @Test
  public void canCreateVendingMachineInstance() {
    assertNotNull(vm);
  }

  @Test
  public void drinkNotFree() {
    Exception exception = assertThrows(NotEnoughMoneyException.class, () -> {
      vm.pressButton("ScottCola");
    });
  }

  @Test
  public void canGetScottColaFor75Cents() throws Exception {
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    Drink drink = vm.pressButton("ScottCola");
    
    assertTrue(drink.isFizzy());
    assertEquals(drink.getName(), "ScottCola");
  }

  @Test
  public void machineResets() throws Exception {
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    Drink drink = vm.pressButton("ScottCola");
    assertNotNull(drink);

    Exception exception = assertThrows(NotEnoughMoneyException.class, () -> {
      vm.pressButton("ScottCola");
    });
  }

  @Test
  public void canGetKarenTeaForOneDollar() throws Exception {

    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    // Test that KarenTea costs more than 75 cents.
    assertThrows(NotEnoughMoneyException.class, () -> {
      vm.pressButton("KarenTea");
    });

    vm.insertQuarter();

    Drink drink = vm.pressButton("KarenTea");
    assertNotNull(drink);
    assertFalse(drink.isFizzy());
    assertEquals(drink.getName(), "KarenTea");
  }

  @Test
  public void otherDrinksUnknown() throws Exception {

    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    assertThrows(UnknownDrinkException.class, () -> {
      vm.pressButton("BessieBooze");
    });
  }
}
