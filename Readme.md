As a ParkingManager   
holding:
    The Plaza Park (9 parking capacity)
    City Mall Garage (12 parking capacity)
    Office Tower Parking (9 parking capacity)
    three Parking Boys with strategy: SequentiallyStrategy | MaxAvailableStrategy | AvailableRateStrategy and above parking lots
    function: getAllParkingLots() -> List<ParkingLot>
  
给我一个park方法，参数有String：strategy，String：plateNumber，会根据strategy选择对应的ParkingBoy去park车，返回一个ParkingTicket。
给我一个fetch方法，参数为String plateNumber，使用plateNumber和Stream API在ParkingLots的tickets中找到对应Ticket，并且第一个ParkingBoy使用这个Ticket进行fetch，返回Car
  
plateNumber的格式有所限制，两个字母-四个数字，例子为"AB-1234”，如果有正则表达式将它抽取成常量
  
帮我编写Junit Test，根据上面的case编写
方法名中使用"_"作为间隔，内容格式为Given->When->Then
使用assert做结果验证
  
把ParkingManager按照JPA，分成三层架构(Controller，Service，Repository)，不使用数据库。Controller的Api暂时不要写
  
帮我编写Junit Test，根据上面的case编写
方法名中使用"_"作为间隔，内容格式为Given->When->Then
使用ParkingManagerRepository的数据，mock模拟ParkingManagerService的方法，assert做结果验证
  
我现在需要一个api来getAllData。
response: 例子为Json: 
{
	"id" : 1,
	"name" : "xxx",
	"tickets" : [
		{
			"plateNumber" : "xxxx",
			"position" : 0,
			"parkingLot" : 1
		}
		............
	]
}
用一个ParkingLotResponseDTO封装，再写一个对应的mapper在service中做转化
  
我现在需要一个api来park。
request:  例子为Json: 
{
	"strategy" :  "SequentiallyStrategy",
	"plateNumber" : "AB-1234"
}
response: Ticket

我现在需要一个api来fetch。
request:  例子为Json: 
{
	"plateNumber" : "AB-1234"
}
response: Car

