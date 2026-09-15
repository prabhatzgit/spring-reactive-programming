What is Reactive Programming?

Why Reactive Programming?

When to use Reactive Programming?


Core Concept of Reactive Programming

1. New Programming Paradigm
2. Asynchronous and non-blocking
3. Functional style code
4. Data flow as event driven stream
5. Backpressure on data streams

![img.png](img.png)

the above picture represents the synchronous and blocking flow. 
it means not accepting the request/order until the previous order/request action is not executed
completely.

If same type or pattern follows, then it delivers very less amount of request per specific time frame.

To resolve above issues, spring reactive framework comes into picture which is asynchronous and non-blocking.

![img_1.png](img_1.png)

By using spring reactive asynchronous and non-blocking we achieve a lot of advantages
1. able to handle more request received by same amount of employee(here waiter) by using 
a publisher and subscriber model.
2. Here Restaurant Owner is a subscriber and cook is a publisher.
3. Owner just subscribe to cooking event and let me know when with event when cook/chef ready with your cook.
4. As a subscriber when Owner/Waiter listen the event from Publisher, subscriber can deliver the food to 
customer.
5. Here Just accept the request/order and process it.
