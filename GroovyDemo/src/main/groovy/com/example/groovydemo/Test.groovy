package com.example.groovydemo

/**
 * @author tengfei
 */
class Test {


    static void main(String[] args) {
        File file = new File("./test.text")

        file.withWriter('utf-8') {
            writer -> writer.write('Hello World')
        }
    }
}
