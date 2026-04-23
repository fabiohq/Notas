<!-- ===================================================== -->
<!-- PROPERTIES -->
<!-- ===================================================== -->
<properties>

    <!-- Java -->
    <java.version>17</java.version>

    <!-- JaCoCo -->
    <jacoco.version>0.8.11</jacoco.version>

    <!-- Variables de entorno -->
    <!-- Tú les colocas los valores por defecto en el sistema -->

    <!-- Archivo .exec -->
    <jacoco.dest.file>${env.JACOCO_DEST_FILE}</jacoco.dest.file>

    <!-- Carpeta reporte -->
    <jacoco.report.dir>${env.JACOCO_REPORT_DIR}</jacoco.report.dir>

</properties>


<!-- ===================================================== -->
<!-- BUILD -->
<!-- ===================================================== -->
<build>

    <plugins>

        <!-- Spring Boot -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>


        <!-- ================================================= -->
        <!-- JACOCO -->
        <!-- ================================================= -->
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>${jacoco.version}</version>

            <executions>

                <!-- Activa cobertura al correr tests -->
                <execution>
                    <id>prepare-agent</id>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>

                    <configuration>
                        <destFile>${jacoco.dest.file}</destFile>
                        <append>true</append>
                    </configuration>
                </execution>


                <!-- Genera reporte -->
                <execution>
                    <id>report</id>
                    <phase>verify</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>

                    <configuration>
                        <dataFile>${jacoco.dest.file}</dataFile>
                        <outputDirectory>${jacoco.report.dir}</outputDirectory>
                    </configuration>
                </execution>

            </executions>

        </plugin>

    </plugins>

</build>