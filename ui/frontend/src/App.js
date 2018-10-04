import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Container, Progress} from 'reactstrap';
import {SearchResults} from "./components/SearchResults";
import {SearchForm} from "./components/SearchForm";

class App extends Component {

    constructor() {
        super();
        this.state = {
            questions: [],
            value: null,
            loading: false,
            showResults: false
        };
    }

    searchQuestions = (value) => {
        this.setState({loading: true, showResults: false});
        this.callApi(value)
            .then((res) => this.setState({
                questions: res,
                loading: false,
                showResults: typeof res !== 'undefined' && res.length > 0
            }))
            .catch((err) => {
                this.setState({loading: false});
                console.log(err);
            });
    };

    callApi = async (value) => {
        if (value == null || value === '') {
            return []
        }
        const response = await fetch('/api/search',
            {
                method: 'POST',
                body:JSON.stringify({text: value}),
                headers:{'content-type': 'application/json'}
            }
        );
        const body = await response.json();
        if (response.status !== 200) throw Error(body.message);
        return body;
    };

    render() {
        return (
            <div className="App">
                <header className="App-search">
                    <SearchForm searchQuestions={this.searchQuestions.bind(this)}/>
                </header>
                <Container className="App-table">
                    { this.state.loading ? <Progress animated color="info" value="100" /> : null }
                    { this.state.showResults ? <SearchResults questions={this.state.questions}/> : null }
                </Container>
                <footer className="footer">
                    <div className="container">
                        <span className="text-muted">This is demo application.</span>
                    </div>
                </footer>
            </div>
        );
    }
}

export default App;
