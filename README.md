# Running in Chrome

```bash
mvn test
```

# Running in Firefox

```bash
mvn -Dbrowser=firefox test
```

If mvn test fails with a 500 error and cannot find the binary, then run with the following parameter:

```bash
mvn -Dbrowser=firefox -Dwebdriver.firefox.bin=/usr/bin/firefox test
```