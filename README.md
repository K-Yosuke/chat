# Chat-Sample

## Description

**SpringBoot** による**WebSocketサーバー**です。  
Clientは [[WebSocket] [Java] WebSocketを試す - DukeLab](http://dukelab.hatenablog.com/entry/2014/02/23/145656) をそのまま参考にしています。  


## How to Use

Connectボタンを押すとサーバーに接続します。  
テキストを入力してSendボタンを押すとチャットができます。  

最終の発言から約1分で強制切断になります。  
接続断時に表示はまだ行っていないので、ブラウザをリロードすると再度Connectできます。  


## Deployment

herokuにデプロイしています  
動いてなかったらアクセスが無くて落ちてます。  
大体一時間だそうです。

https://secret-plains-89009.herokuapp.com/chat.html  
