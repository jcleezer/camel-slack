# Camel Slack Component

The **slack** component allows you to connect to an instance of [Slack](http://www.slack.com) and delivers a message contained in the message body via a pre established [Slack incoming webhook](https://api.slack.com/incoming-webhooks).

## URI format

To send a message to a channel.

```
slack:#channel[?options]
```

To send a direct message to a slackuser.

```
slack:@username[?options]
```

The Slack component only supports producer endpoints so you cannot use this component at the beginning of a route to listen to messages in a channel.

## Options

These options will 

| Option | Example | Description |
| ------ | ------- | ----------- |
| `username` | `username=CamelUser` | This is the username that the bot will have when sending messages to a channel or user. |
| `iconUrl` | `iconUrl=http://somehost.com/avatar.gif` | The avatar that the component will use when sending message to a channel or user. |
| `iconEmoji` | `iconEmoji=:camel:` | Use a Slack emoji as an avatar |
