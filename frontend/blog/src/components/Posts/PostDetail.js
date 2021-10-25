import React, { Component } from 'react';
import Post from './Post';
import styled from 'styled-components';
import { getPost } from '../../utils/api';
import resumeData from '../../resumeData';
import VerticalBar from '../../components/VerticalBar/VerticalBar';

const Container = styled.div`
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  width: 100%;
  margin-top: 50px;
`;
class PostDetail extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      loading: true,
    };
  }
componentDidMount() {
    getPost(this.props.idPost)
      .then((res) => {
        this.setState({
          post: res.data,
          loading: false,
        });
      })
      .catch((err) => console.log(err));
  }
renderPost = () => {
    const { post } = this.state;
    return (
            <Post
              id={post.id}
              title={post.title}
              body={post.body}
              author={post.author}
            />
          );

  }
render() {
    const { loading } = this.state;
    return (
      <Container className="row">
        <div className="twelve columns main-col">
            {loading ? 'loading...' : this.renderPost()}
        </div>
      </Container>

    );
  }
}
export default PostDetail;