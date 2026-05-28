2026-05-28 11:25:08.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$2@31671157
2026-05-28 11:25:08.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:13.545 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:556]: Selected key sun.nio.ch.SelectionKeyImpl@3b569985 for sun.nio.ch.ServerSocketChannelImpl[/0:0:0:0:0:0:0:0:8080]
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@e6c51a9
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$1@59ea570b
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener Accepting listener for io.undertow.server.protocol.http.HttpOpenListener@5dd5c8ee on channel TCP server (NIO) <18d75d30>
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener io.undertow.server.protocol.http.HttpOpenListener@5dd5c8ee on channel org.xnio.nio.NioSocketStreamConnection@68bce6a5
2026-05-28 11:25:13.546 [correlation-id: ] [frame-engine,,] [TRACE] [io.undertow.server.protocol.http.HttpOpenListener] [handleEvent:106]: Opened connection with /10.120.6.2:37596
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.IoUtils] [safeClose:133]: Closing resource io.undertow.server.protocol.http.HttpServerConnection@64451788
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.NioSocketStreamConnection] [closeAction:134]: Cancelling key sun.nio.ch.SelectionKeyImpl@30cd24b7 of java.nio.channels.SocketChannel[connected ishut local=/10.120.7.56:8080 remote=/10.120.6.2:37596] (same thread)
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener io.undertow.server.AbstractServerConnection$CloseSetter@3f66efa1 on channel org.xnio.nio.NioSocketStreamConnection@68bce6a5
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@e6c51a9
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$2@31671157
2026-05-28 11:25:13.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:14.156 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]: QCCAJD1****************************************
2026-05-28 11:25:14.156 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:                 TargetSize:	       200
2026-05-28 11:25:14.156 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:            AllocationCount:	       200
2026-05-28 11:25:14.156 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:      FailedAllocationCount:	         0
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:         LeakedObjectsCount:	         0
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:            -- Percentile --	       25%	       50%	       75%	      100%
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:   AllocationFailureLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:          AllocationLatency:	   1673.00	   1681.00	   1688.00	   3048.00
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:        DeallocationLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:             ObjectLifetime:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]: ReallocationFailureLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.157 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:        ReallocationLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.158 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]: ****************************************
2026-05-28 11:25:14.539 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]: QCTFD1****************************************
2026-05-28 11:25:14.540 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:                 TargetSize:	       200
2026-05-28 11:25:14.540 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:            AllocationCount:	       200
2026-05-28 11:25:14.540 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:      FailedAllocationCount:	         0
2026-05-28 11:25:14.540 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:         LeakedObjectsCount:	         0
2026-05-28 11:25:14.540 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:            -- Percentile --	       25%	       50%	       75%	      100%
2026-05-28 11:25:14.540 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:   AllocationFailureLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.541 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:          AllocationLatency:	   1669.00	   1679.00	   1690.00	   3017.00
2026-05-28 11:25:14.541 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:        DeallocationLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.541 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:             ObjectLifetime:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.541 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]: ReallocationFailureLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.541 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]:        ReallocationLatency:	      0.00	      0.00	      0.00	      0.00
2026-05-28 11:25:14.541 [correlation-id: ] [frame-engine,,] [DEBUG] [co.com.bsnc.motortramas.D.D] [A:-1]: ****************************************
2026-05-28 11:25:18.545 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.545 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:556]: Selected key sun.nio.ch.SelectionKeyImpl@3b569985 for sun.nio.ch.ServerSocketChannelImpl[/0:0:0:0:0:0:0:0:8080]
2026-05-28 11:25:18.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:556]: Selected key sun.nio.ch.SelectionKeyImpl@3b569985 for sun.nio.ch.ServerSocketChannelImpl[/0:0:0:0:0:0:0:0:8080]
2026-05-28 11:25:18.546 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@1dc99048
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$1@59ea570b
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener Accepting listener for io.undertow.server.protocol.http.HttpOpenListener@5dd5c8ee on channel TCP server (NIO) <18d75d30>
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@66bf654a
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener io.undertow.server.protocol.http.HttpOpenListener@5dd5c8ee on channel org.xnio.nio.NioSocketStreamConnection@2c451484
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$1@59ea570b
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [io.undertow.server.protocol.http.HttpOpenListener] [handleEvent:106]: Opened connection with /10.120.6.2:37610
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener Accepting listener for io.undertow.server.protocol.http.HttpOpenListener@5dd5c8ee on channel TCP server (NIO) <18d75d30>
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.IoUtils] [safeClose:133]: Closing resource io.undertow.server.protocol.http.HttpServerConnection@717fffd3
2026-05-28 11:25:18.547 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener io.undertow.server.protocol.http.HttpOpenListener@5dd5c8ee on channel org.xnio.nio.NioSocketStreamConnection@2793c9e
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.NioSocketStreamConnection] [closeAction:134]: Cancelling key sun.nio.ch.SelectionKeyImpl@367e2fc4 of java.nio.channels.SocketChannel[connected ishut local=/10.120.7.56:8080 remote=/10.120.6.2:37610] (same thread)
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [io.undertow.server.protocol.http.HttpOpenListener] [handleEvent:106]: Opened connection with /10.120.6.2:37600
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener io.undertow.server.AbstractServerConnection$CloseSetter@549e6753 on channel org.xnio.nio.NioSocketStreamConnection@2c451484
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@1dc99048
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$2@31671157
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.IoUtils] [safeClose:133]: Closing resource io.undertow.server.protocol.http.HttpServerConnection@11060a79
2026-05-28 11:25:18.548 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.NioSocketStreamConnection] [closeAction:134]: Cancelling key sun.nio.ch.SelectionKeyImpl@700c65df of java.nio.channels.SocketChannel[connected ishut local=/10.120.7.56:8080 remote=/10.120.6.2:37600] (same thread)
2026-05-28 11:25:18.549 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.ChannelListeners] [invokeChannelListener:91]: Invoking listener io.undertow.server.AbstractServerConnection$CloseSetter@6ac89d41 on channel org.xnio.nio.NioSocketStreamConnection@2793c9e
2026-05-28 11:25:18.549 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@66bf654a
2026-05-28 11:25:18.549 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:537]: Selected on sun.nio.ch.EPollSelectorImpl@4fd7be91
2026-05-28 11:25:18.549 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [safeRun:581]: Running task org.xnio.nio.QueuedNioTcpServer$2@31671157
2026-05-28 11:25:18.549 [correlation-id: ] [frame-engine,,] [TRACE] [org.xnio.nio.WorkerThread] [run:505]: Beginning select on sun.nio.ch.EPollSelectorImpl@4fd7be91
