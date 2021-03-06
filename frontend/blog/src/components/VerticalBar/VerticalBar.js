import React, { Component } from 'react';
import resumeData from '../../resumeData';
export default class VerticalBar extends Component {
  render() {
    return (
            <section id="blog_left_bar">
                <img className="profile-pic"  src="images/rlb.webp" alt="" />
                <h2 >{resumeData.name}</h2>
                <p>
                {
                    resumeData.aboutme
                }
                </p>
                {
                    resumeData.socialLinks && resumeData.socialLinks.map((item)=>{
                        return(
                            <p key={item.name}>
                                <a href={item.url}>
                                    <i className={item.className} /> {item.name}
                                </a>
                            </p>
                            )
                        })
                }
            </section>
        );
  }
}