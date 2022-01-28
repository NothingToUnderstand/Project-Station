Hi,
My name is Viktor
It's task on position Junior Java Developer
There are two app:Ticket Service on localhost:8081 and Payment Service on localhost:8080
To start them you can double click the bat_file in this directory
1.To save ticket:   http://localhost:8081/ticket/save   +RequestParam:idPasList,Name,Surname    -Response:idTicket
2.Post from Save ticket(1) to Save Payment(3) 
3.Save payment:   http://localhost:8080/payment +RequestBody:idTicket,idPasList,Name,Surname and (-cost),cost of the ticket with negative meaning
4.To Pay for the ticket: http://localhost:8080/payment/pay +RequestParam:idTicket,cost  -Response:idPay
5.Post from Pay(4) to Info Save(6) 
6.Info Save: http://localhost:8081/info/save +RequestBody:idPay,idTicket,idPasList,Name,Surname -Response:idInfo
7.Info info: http://localhost:8081/info/get +RequestParam:idTicket  -Response:idTicket,idPasList,status
Thank you