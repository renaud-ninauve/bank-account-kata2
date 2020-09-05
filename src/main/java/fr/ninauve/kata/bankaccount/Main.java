package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.Action;
import fr.ninauve.kata.bankaccount.action.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    private final MenuAction menuAction;

    @Autowired
    public Main(MenuAction menuAction) {

        this.menuAction = menuAction;
    }

    public void execute() {

        Action action = menuAction;
        while ((action = action.execute()) != null) ;
    }

    public static void main(String... args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        final Main main = ctx.getBean(Main.class);
        main.execute();
    }
}
