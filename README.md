<properties>
    <jacoco.dest.file>${project.build.directory}/jacoco/jacoco.exec</jacoco.dest.file>
    <jacoco.report.dir>${project.build.directory}/site/jacoco</jacoco.report.dir>
</properties>

<profiles>
    <profile>
        <id>env-jacoco</id>
        <activation>
            <property>
                <name>env.JACOCO_DEST_FILE</name>
            </property>
        </activation>

        <properties>
            <jacoco.dest.file>${env.JACOCO_DEST_FILE}</jacoco.dest.file>
        </properties>
    </profile>
</profiles>