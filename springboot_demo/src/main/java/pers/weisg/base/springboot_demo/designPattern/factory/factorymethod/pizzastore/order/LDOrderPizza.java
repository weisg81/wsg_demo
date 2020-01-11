package pers.weisg.base.springboot_demo.designPattern.factory.factorymethod.pizzastore.order;


import pers.weisg.base.springboot_demo.designPattern.factory.factorymethod.pizzastore.pizza.LDCheesePizza;
import pers.weisg.base.springboot_demo.designPattern.factory.factorymethod.pizzastore.pizza.LDPepperPizza;
import pers.weisg.base.springboot_demo.designPattern.factory.factorymethod.pizzastore.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {


	@Override
	Pizza createPizza(String orderType) {

		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		// TODO Auto-generated method stub
		return pizza;
	}

}
