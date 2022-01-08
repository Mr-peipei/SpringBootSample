import logo from './logo.svg';
import './App.css';
import fetch from "node-fetch";
import {Component} from "react";

class App extends Component{
  constructor (props) {
    super(props)
    this.state= {
      tweet: '',
      tweetId: ''
    }
  }
  componentWillMount() {
    const URL = 'http://localhost:8080/api/sample'
    fetch(URL, {mode: 'cors'})
        .then(res=>res.json())
        .then(json=> {
          this.setState({
            tweet: json['tweet'],
            tweetId: json['tweetId']
          })
        });
  }

  render() {
     return <div className="App">
       ツイート: {this.state.tweet} <br/>
       ツイートID: {this.state.tweetId} <br/>
    </div>
  }
}

export default App;
