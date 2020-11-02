package main

import (
	"fmt"
	"log"
)

type TestError struct {

}

func (*TestError) Error() string {
	return "error"
}

func test(x int) (int, error) {
	var err *TestError

	if x < 0 {
		err = new(TestError)
		x = 0
	}else {
		x += 100
	}
	return x, err
}

func main()  {

	var a interface{} = nil
	var b interface{} = (*int)(nil)

	println(a == nil, b == nil)

	fmt.Println("++++++++++++++++")
	x, err := test(100)
	if err != nil {
		log.Fatalln("err != nil")
	}

	println(x)
}
