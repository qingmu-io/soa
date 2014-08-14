package org.soa.spring.security.util;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AntUrlPathMatcher implements UrlMatcher {

        private boolean requiresLowerCaseUrl;
        private PathMatcher pathMatcher;

        public AntUrlPathMatcher() {
                this(true);
        }

        public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
                this.requiresLowerCaseUrl = true;
                this.pathMatcher = new AntPathMatcher();

                this.requiresLowerCaseUrl = requiresLowerCaseUrl;
        }

        public Object compile(String path) {
                if (this.requiresLowerCaseUrl) {
                        return path.toLowerCase();
                }

                return path;
        }

        public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
                this.requiresLowerCaseUrl = requiresLowerCaseUrl;
        }

        public boolean pathMatchesUrl(Object path, String url) {
                if (("/**".equals(path)) || ("**".equals(path))) {
                        return true;
                }
                return this.pathMatcher.match((String) path, url);
        }

        public String getUniversalMatchPattern() {
                return "/**";
        }

        public boolean requiresLowerCaseUrl() {
                return this.requiresLowerCaseUrl;
        }

        public String toString() {
                return super.getClass().getName() + "[requiresLowerCase='" + this.requiresLowerCaseUrl + "']";
        }

}
