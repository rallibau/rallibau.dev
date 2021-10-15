import React, { Component } from 'react';
import Post from './Post';
import styled from 'styled-components';
import { getPosts } from '../../utils/api';
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
    getPosts()
      .then((res) => {
        this.setState({
          posts: res.data,
          loading: false,
        });
      })
      .catch((err) => console.log(err));
  }
renderPosts = () => {
    const { posts } = this.state;
    return posts.map(post => {

      const { title, body, id } = post;

      return (
        <Post
          id={id}
          title={title}
          body={body}
        />
      );
    });
  }
render() {
    const { loading } = this.state;
    return (
      <Container className="row">
        <div className="three columns align-left">
            <VerticalBar resumeData={resumeData}/>
        </div>
        <div className="nine columns main-col">
            {loading ? 'loading...' : this.renderPosts()}
        </div>
      </Container>

    );
  }
}
export default PostDetail;