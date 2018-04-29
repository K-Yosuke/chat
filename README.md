# Chat-Sample

## Description

**SpringBoot** による**WebSocketサーバー**です。  
Clientは [[WebSocket] [Java] WebSocketを試す - DukeLab](http://dukelab.hatenablog.com/entry/2014/02/23/145656) をそのまま参考にしています。  


## How to Use

Connectボタンを押すとサーバーに接続します。  
テキストを入力してSendボタンを押すとチャットができます。  

最終の発言から約1分で強制切断になります。  
ブラウザをリロードすると再度Connectできます。  
接続断時の表示は未実装です。切れたと思ったらリロードしてください。  


## Deployment

herokuにデプロイしています  
動いてなかったらアクセスが無くて落ちてます。  
大体一時間で落ちるそうです。

https://secret-plains-89009.herokuapp.com/chat.html  
