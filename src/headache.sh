#/bin/bash

find . -name '*.java' -exec \
    headache -h headache-copyright -c headache-config '{}' \;