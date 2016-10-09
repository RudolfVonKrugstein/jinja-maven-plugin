# jinja-maven-plugin

This project combines [jinjava](https://github.com/HubSpot/jinjava) and [snakeyaml](https://bitbucket.org/asomov/snakeyaml) to compile jinja template files with variable defintions in a yaml file from a maven project.

## Usage

In your project file do:

```
...
  <build>
    <plugins>
      <plugin>
        <groupId>de.wintercloud</groupId>
        <artifactId>jinja-maven-plugin</artifactId>
        <version>1.0</version>
        <configuration>
          <outputFile>path-to-outout.txt</outputFile>
          <templateFile>path-to-template.jinja</templateFile>
          <varFile>path-to-variables.yaml</varFile>
        </configuration>
      </plugin>
    </plugins>
  </build>
...
```

and then execute the goal using

```
mvn de.wintercloud:jinja-maven-plugin:1.0:renderjinja
```

or make it part of your compile phase:

```
  <build>
    <plugins>
      <plugin>
        <groupId>de.wintercloud</groupId>
        <artifactId>jinja-maven-plugin</artifactId>
        <version>1.0</version>
        <executions>
          <execution>
            <phase>compile</phase>
            <goals>
              <goal>renderjinja</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <outputFile>path-to-out.txt</outputFile>
          <templateFile>path-to-template.jinja</templateFile>
          <varFile>path-to-variables.yaml</varFile>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
