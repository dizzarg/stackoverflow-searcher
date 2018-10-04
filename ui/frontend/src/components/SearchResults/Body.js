import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Question} from "./Question";

export class Body extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tbody>
            {this.props.questions.map((question, id) => {
                return (<Question key={id} id={id} question={question} />)
            })}
            </tbody>
        )
    }

}

/**
 return (
 <tr key={i}>
 <td key="#">{i + 1}</td>
 <td key={question.title}>
 <a target="_blank" href={question.link} dangerouslySetInnerHTML={{__html: question.title}}/>
 </td>
 <td key={question.creationDate}>
 <Moment format="YYYY/MM/DD">
 {new Date(question.creationDate * 1000)}
 </Moment>
 </td>
 <td key={question.answered}>
 {question.answered && <FontAwesomeIcon icon={ faCheck } /> }
 </td>
 <td>{question.tags.map((tag, i) => (
                            <Badge key={i} color="secondary">{tag}</Badge>
                        ))}
 </td>
 <td key={question.score}>{question.score}</td>
 <td key={question.viewCount}>{question.viewCount}</td>
 <td key={question.owner}>
 <a target="_blank" href={question.owner.link}>
 <FontAwesomeIcon icon={ faUser } />&nbsp;
 <span dangerouslySetInnerHTML={{__html: question.owner.name}}/>
 </a>
 </td>
 </tr>
 )
 */