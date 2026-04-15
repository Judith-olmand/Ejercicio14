package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        int idDep;
        double salario;
        int idEmp;

        String sqlDepartamento = "INSERT INTO departamento (dep_id, nombre) VALUES (?,?)";
        String sqlEmpleado = "INSERT INTO empleado (emp_id , nombre, salario, dep_id) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement();
                PreparedStatement ps = conn.prepareStatement(sqlDepartamento);
                PreparedStatement ps2 = conn.prepareStatement(sqlEmpleado)) {
            try {
                ps.setInt(1,10);
                ps.setString(2, "RRHH");
                ps.executeUpdate();

                ps.setInt(1,20);
                ps.setString(2, "IT");
                ps.executeUpdate();

                ps.setInt(1,30);
                ps.setString(2, "Seguridad");
                ps.executeUpdate();

                ps.setInt(1,40);
                ps.setString(2, "Expediciones");
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println("Error al insertar el departamento");
            }

            for (int i = 0 ; i < 5 ; i++){
                try {
                    String sql = "SELECT MAX(emp_id) FROM empleado";
                    ResultSet rs = ps.executeQuery(sql);
                    rs.next();
                    idEmp = rs.getInt(1) + 1;

                    try {
                        System.out.println("Ingrese el nombre del empleado");
                        nombre = sc.nextLine();
                        System.out.println("Ingrese el salario del empleado");
                        salario = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Ingrese el id del departamento");
                        idDep = sc.nextInt();
                        sc.nextLine();

                        ps2.setInt(1, idEmp);
                        ps2.setString(2, nombre);
                        ps2.setDouble(3, salario);
                        ps2.setInt(4, idDep);
                        ps2.executeUpdate();
                        System.out.println("Empleado añadido correctamente");
                    } catch (Exception e){
                        System.out.println("Error al insertar el empleado");
                    }
                } catch (Exception e){
                    System.out.println("Error en la consulta");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}