package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class Main {

    private final Console console;

    @Autowired
    public Main(Console console) {

        this.console = console;
    }

    public void execute() {

        console.printLines(singletonList("Hello world"));
    }

    public static void main(String... args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        final Main main = ctx.getBean(Main.class);
        main.execute();
    }
}
