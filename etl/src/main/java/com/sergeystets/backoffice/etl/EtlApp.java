package com.sergeystets.backoffice.etl;


import com.beust.jcommander.JCommander;
import com.sergeystets.backoffice.etl.job.EtlJob;
import com.sergeystets.backoffice.etl.job.EtlJobParams;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EtlApp implements CommandLineRunner {

    private final EtlJob etl;

    public static void main(String[] args) {
        SpringApplication.run(EtlApp.class, args);
    }

    public void run(String... args) {
        final EtlJobParams params = new EtlJobParams();
        JCommander jCommander = new JCommander();
        jCommander.addCommand(params);
        try {
            jCommander.parse(args);
            if (params.isHelp() || args.length == 0) {
                jCommander.usage(EtlJobParams.COMMAND_NAME);
            } else {
                System.out.println("etl job started");
                etl.run(params);
                System.out.println("etl job finished successfully");
            }
        } catch (Exception e) {
            System.err.print("etl job failed: ");
            e.printStackTrace();
            System.out.println("\n\nuse '" + EtlJobParams.COMMAND_NAME + " -h' to get help");
        }
    }
}
