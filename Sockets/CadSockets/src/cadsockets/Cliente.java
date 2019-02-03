package cadsockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Paulo Ricardo Miranda Fontes
 */
public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int idConta;
        float valor;

        String resposta;

        Conta retorno = null;

        Socket cliente = new Socket("127.0.0.1", 7070);
        DataOutputStream outVar = new DataOutputStream(cliente.getOutputStream());
        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

        Conta conta = new Conta();

        Scanner ler = new Scanner(System.in);
        int op = 0;

        do {

            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Criar nova conta.");
            System.out.println("2 - Visualizar saldo.");
            System.out.println("3 - Sacar da conta.");
            System.out.println("4 - Depositar na conta.");
            System.out.println("0 - Sair.");

            op = ler.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite ID da conta a ser criada");
                    ler.nextLine();
                    System.out.print(">");
                    conta.setId(Integer.parseInt(ler.nextLine()));

                    outVar.write(op);
                    outVar.flush();

                    out.writeObject(conta);
                    out.flush();

                    resposta = in.readLine();

                    System.out.println(resposta);
                    break;

                case 2:
                    System.out.print("Digite o ID da conta para verificação do saldo:");
                    ler.nextLine();
                    System.out.print(">");

                    idConta = Integer.parseInt(ler.nextLine());

                    outVar.write(op);
                    out.flush();

                    out.writeObject(idConta);
                    out.flush();

                    retorno = (Conta) in.readObject();

                    System.out.print(retorno);
                    break;

                case 3:
                    System.out.println("Digite o ID da conta para o saque:");
                    ler.nextLine();
                    System.out.print(">");

                    idConta = Integer.parseInt(ler.nextLine());

                    System.out.println("Digite valor a ser saque:");
                    ler.nextLine();
                    System.out.print(">");

                    valor = Float.parseFloat(ler.nextLine());

                    outVar.write(op); //Operação
                    out.flush();

                    out.writeObject(idConta); //ID da conta
                    out.flush();

                    out.writeObject(valor); //Valor do deposito
                    out.flush();

                    retorno = (Conta) in.readObject();

                    System.out.println(retorno);
                    break;

                case 4:
                    System.out.println("Digite o ID da conta para o deposito:");
                    ler.nextLine();
                    System.out.print(">");

                    idConta = Integer.parseInt(ler.nextLine());

                    System.out.println("Digite valor a ser depositado:");
                    ler.nextLine();
                    System.out.print(">");

                    valor = Float.parseFloat(ler.nextLine());

                    outVar.write(op); //Operação
                    out.flush();

                    out.writeObject(idConta); //ID da conta
                    out.flush();

                    out.writeObject(valor); //Valor do deposito
                    out.flush();

                    retorno = (Conta) in.readObject();

                    System.out.println(retorno);
                    break;

                case 0:
                    outVar.write(op);
                    outVar.flush();
                    break;

                default:
                    System.out.println("Opção inválida!!!!");
            }

        } while (op != 0);

    }

}
